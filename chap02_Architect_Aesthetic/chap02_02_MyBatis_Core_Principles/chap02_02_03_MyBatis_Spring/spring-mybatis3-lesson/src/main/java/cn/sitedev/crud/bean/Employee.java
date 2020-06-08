package cn.sitedev.crud.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Employee implements Serializable {
    private Integer empId;
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})", message = "用户名必须是2-5位中文或6-16位英文和数字的组合！")
    private String empName;

    private String gender;
    @Pattern(regexp = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$", message = "邮箱格式不正确！")
    private String email;

    private Integer dId;

    private Department department;

    public Employee() {
    }

    public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }

}