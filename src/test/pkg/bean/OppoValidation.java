package test.pkg.bean;

import lombok.Data;

@Data
public class OppoValidation {
    private Long id;
    private String oppoId;
    private String name;
    private Long minimum;
    private Long maximum;
    private PayTypes payTypes;
    private String phoneNum;
    private Boolean status;
    private Boolean hasSms;
}
