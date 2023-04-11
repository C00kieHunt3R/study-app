package orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orm.dto.UserAccountDto;
import orm.model.UserAccount;
import orm.exception.NoSuchEntityFieldException;
import orm.repository.UserAccountRepository;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository repository;

    public UserAccountDto findUserByUsername(String username) {
        UserAccount userAccount = this.repository.findUserAccountByUsername(username).orElseThrow(() ->{
                    throw new NoSuchEntityFieldException(UserAccount.class.getSimpleName(), "username", username);
        });
        return UserAccountDto.fromEntity(userAccount);
    }


}
