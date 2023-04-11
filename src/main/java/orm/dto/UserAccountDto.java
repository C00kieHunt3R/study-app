package orm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import orm.model.UserAccount;
import orm.model.enums.UserRole;

@Getter
@Setter
@Builder
public class UserAccountDto {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private UserRole role;

    public static UserAccountDto fromEntity(UserAccount userAccount) {
        return UserAccountDto.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .password(userAccount.getPassword())
                .role(userAccount.getRole())
                .build();
    }

    public static UserAccount toEntity(UserAccountDto dto) {
        return new UserAccount(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getRole()
        );
    }
}
