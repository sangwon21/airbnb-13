/** @jsx jsx */
import { jsx, css } from '@emotion/core';
import FlexLayout from '@Custom/FlexLayout/FlexLayout';
import Row from './Row/Row';
import Button from '@Custom/Button/Button';
import { useDispatch, useSelector } from 'react-redux';
import { setGuestFilter } from '@Action/filterAction';
import { RootState } from '@Reducer/index';

const GuestFilter = () => {
  const dispatch = useDispatch();
  const { adult, child, baby } = useSelector((state: RootState) => state.filterReducer);
  const reset = () => {
    dispatch(setGuestFilter('reset', 0));
  };

  return (
    <div css={style}>
      <FlexLayout direction={'column'} align={'left'} gap={'1rem'}>
        <Row
          type="성인"
          range={'만 13세 이상'}
          count={adult}
          setCount={(count: number) => dispatch(setGuestFilter('adult', count))}
        />
        <Row
          type="어린이"
          range={'2~12세'}
          count={child}
          setCount={(count: number) => dispatch(setGuestFilter('child', count))}
        />
        <Row
          type="유아"
          range={'2세 미만'}
          count={baby}
          setCount={(count: number) => dispatch(setGuestFilter('baby', count))}
        />
      </FlexLayout>
      <FlexLayout direction={'row'} align={'spaceBetween'} customCSS={customCSS}>
        <Button theme={'nooutline'} fontSize="medium" width={'3rem'} onClick={reset}>
          지우기
        </Button>
        <Button theme={'secondary'} fontSize="medium" width={'3rem'}>
          저장
        </Button>
      </FlexLayout>
    </div>
  );
};

const style = css`
  width: 400px;
  padding: 1rem;
  border-radius: 1rem;
  background-color: #ffffff;
  border: 1px solid #eeeeee;
  position: absolute;
`;

const customCSS = css`
  border-top: 1px solid #eeeeee;
  margin-top: 2rem;
  padding-top: 1rem;
`;

export default GuestFilter;
