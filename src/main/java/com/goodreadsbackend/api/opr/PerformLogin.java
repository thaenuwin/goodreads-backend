package com.goodreadsbackend.api.opr;

import com.goodreadsbackend.api.domain.UserLoginResponse;

public interface PerformLogin {

    UserLoginResponse performUserLogin(String username, String password);

}
