package com.clinicware.data;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
@Component
public interface UserRepository extends CrudRepository<User, String> {

    User findUserByUsername(String username);

//    @Procedure(procedureName = "INS_MSYS_USERS_P")
//    Map<String, Object> saveUser(
//                 @Param("P_USERNAME") String username,
//                 @Param("P_PASSWORD") String password,
//                 @Param("P_FIRST_NAME") String firstName,
//                 @Param("P_LAST_NAME")String lastName,
//                 @Param("P_TITLE")String title,
//                 @Param("P_USER_TYPE")int type,
//                 @Param("P_ENTRY_USER") int entryUser,
//                 @Param("P_ENTRY_DEVICE")String device);

}
