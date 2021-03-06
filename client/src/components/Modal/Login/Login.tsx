import React from 'react';
import Button from '@Custom/Button/Button';
import Icon from '$Icon/Icon';
import { login } from '$Util/cookie/cookie';

const Login = () => {
  return (
    <div>
      <Button theme={'primary'} fontSize={'medium'} width={'100%'} onClick={login}>
        <Icon icon="github" />
        <span>Github으로 로그인</span>
      </Button>
    </div>
  );
};

export default Login;
