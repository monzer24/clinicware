package com.clinicware.data.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "msys_users")
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
//@NamedStoredProcedureQueries(
//        @NamedStoredProcedureQuery(
//                name = "registerNewUser",
//                procedureName = "INS_MSYS_USERS_P",
//                parameters = {
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USERNAME", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PASSWORD", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FIRST_NAME", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_LAST_NAME", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TITLE", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_TYPE", type = Integer.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ENTRY_USER", type = Integer.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ENTRY_DEVICE", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_ID", type = Integer.class),
//                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_TRX_STATUS", type = Integer.class),
//                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_ERROR_MESSAGE", type = String.class)
//                }
//        )
//)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    public User(String username, String password, String firstName, String lastName, String title, String type, int clinicID) {
        this.id = "";
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.userType = type;
        this.clinicID = clinicID;
    }

//    public User(String id, String username, String password, String firstName, String lastName, String title, String userType) {
//        System.out.println("here");
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.title = title;
//        this.userType = userType;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "USER_CODE")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "title")
    private String title;
    @Column(name = "user_type")
    private String userType;
    @Column(name ="clinic_id")
    private int clinicID;

}
