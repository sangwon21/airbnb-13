import React from 'react';
import { TURN_ON_MODAL, TURN_OFF_MODAL, ModalAction, turnOnModal } from '../action/modalAction';
import modals from '../components/Modal/AvailableModal';
import Reservation from '@Modal/Reservation/Reservation';

type ModalState = {
  on: boolean;
  content: () => JSX.Element | null;
};

const initialState = {
  on: false,
  content: Reservation
};

const modalReducer = (state: ModalState = initialState, action: ModalAction) => {
  switch (action.type) {
    case TURN_ON_MODAL:
      const newAction = action as ReturnType<typeof turnOnModal>;
      return {
        on: true,
        content: modals[newAction.modalType]
      };
    case TURN_OFF_MODAL:
      return {
        on: false
      };
    default:
      return {
        ...state
      };
  }
};

export default modalReducer;