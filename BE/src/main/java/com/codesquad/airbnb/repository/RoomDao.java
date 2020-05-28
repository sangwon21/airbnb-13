package com.codesquad.airbnb.repository;

import com.codesquad.airbnb.dto.ReservationForm;
import com.codesquad.airbnb.dto.Room;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class RoomDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final RoomMapper roomMapper = new RoomMapper();

    public RoomDao(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Room> findByCondition(int offset, int limit,
                                      int adults, int children, int infants,
                                      String checkIn, String checkOut,
                                      int minPrice, int maxPrice) {
        int totalGuest = adults + children + infants;

        String roomsSql = "SELECT id, title, thumbnail, super_host, address, location, accommodates, " +
                "price, cleaning_fee, review_score " +
                "from room " +
                "WHERE id not in ( select distinct (r.room_id) " +
                "from reservation_date rd left join reservation r on rd.reservation_id = r.id where rd.reservation_date between :checkIn and :checkOut) " +
                "and room.accommodates >= :totalGuest " +
                "and room.price >= :minPrice ";

        if (maxPrice != 0) {
            roomsSql += "and price <= :maxPrice ";
        }

        roomsSql += "limit :limit offset :offset";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("totalGuest", totalGuest)
                .addValue("minPrice", minPrice)
                .addValue("maxPrice", maxPrice)
                .addValue("checkIn", checkIn)
                .addValue("checkOut", checkOut)
                .addValue("limit", limit)
                .addValue("offset", offset);

        return namedJdbcTemplate.query(roomsSql, parameters, roomMapper);
    }

    public Long addReservation(Long roomId, ReservationForm reservationForm) {
        // todo
        // user_id 작업하기
        String sql = "INSERT INTO reservation (adult, child, infant, user_id, room_id) " +
                "VALUES (:adult, :child, :infant, :userId, :roomId)";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("adult", reservationForm.getAdults())
                .addValue("child", reservationForm.getChildren())
                .addValue("infant", reservationForm.getInfants())
                .addValue("userId", 1L)
                .addValue("roomId", roomId);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(sql, parameters, keyHolder);

        return keyHolder.getKey().longValue();
    }


    public void addReservationDate(Long reservationId, Date date) {
        String sql = "INSERT INTO reservation_date (reservation_id , reservation_date)" +
                "VALUES (:reservationId, :reservationDate)";
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("reservationId", reservationId)
                .addValue("reservationDate", date);

        namedJdbcTemplate.update(sql, parameters);
    }

}
