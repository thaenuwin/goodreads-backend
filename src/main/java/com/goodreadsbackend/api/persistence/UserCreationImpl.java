package com.goodreadsbackend.api.persistence;

import com.goodreadsbackend.api.opr.UserCreation;
import com.goodreadsbackend.api.persistence.entity.UserData;
import com.goodreadsbackend.api.persistence.entity.UserLogin;
import com.goodreadsbackend.api.util.AccountStatus;
import com.goodreadsbackend.api.util.PasswordUtil;
import com.goodreadsbackend.api.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class UserCreationImpl implements UserCreation {

    @Autowired
    UserLoginRepo userLoginRepo;

    @Autowired
    UserDataRepo userDataRepo;

    @Override
    public CreateUserResponse userCreate(UserCreationCmd usercmd) {
        ValidatorUtil.validate(usercmd);

        String userId = usercmd.getUserId();
        if (isExistingUserLogin(userId)) {
            return CreateUserResponse.ERROR_DUPLICATE_USER_FOUND;
        }

        if(isExistingEmail(usercmd.getEmail())){
            return CreateUserResponse.ERROR_DUPLICATE_EMAIL_WITH_OTHER_USER;
        }

        UserData userData = UserData.builder()
                .userId(usercmd.getUserId())
                .email(usercmd.getEmail())
                .displayName(usercmd.getDisplayName())
                .phoneNumber(usercmd.getPhoneNumber())
                .status(AccountStatus.ACTIVE.getDisplayName())
                .updatedDate(new Date())
                .createdDate(new Date())
                .build();
        userDataRepo.save(userData);

        String hashPassword= PasswordUtil.hashPassword(usercmd.getLoginPassword());
        UserLogin userLogin = UserLogin.builder()
                .userId(usercmd.getUserId())
                .loginPassword(hashPassword)
                .enabled(1)
                .build();

        userLoginRepo.save(userLogin);
        return CreateUserResponse.SUCCESS;
    }

    private boolean isExistingUserLogin(String userId) {
        log.debug("isExistingUserId: "+userId);
        UserLogin data = userLoginRepo.findByUserId(userId);
        return data != null && data.getUserId() != null;
    }

    private boolean isExistingEmail(String email) {
        log.debug( "isExistingEmail: "+email);
        UserData data = userDataRepo.findByEmail(email);
        return data != null && data.getEmail() != null;
    }
}
