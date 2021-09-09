package com.test.user.transformer;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends Message {
private String userName;
private String userEmail;
private Date lastLoginDate;
}
