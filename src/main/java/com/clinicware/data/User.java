package com.clinicware.data;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "msys_users")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "registerNewUser",
                procedureName = "INS_MSYS_USERS_P",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USERNAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PASSWORD", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FIRST_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_LAST_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TITLE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_TYPE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ENTRY_USER", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ENTRY_DEVICE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_TRX_STATUS", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_ERROR_MESSAGE", type = String.class)
                }
        )
)
public class User implements Serializable {

    public User(String username, String password, String firstName, String lastName, String title, int type) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.userType = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String title;
    private int userType;

}
