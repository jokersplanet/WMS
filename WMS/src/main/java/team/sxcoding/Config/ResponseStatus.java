<<<<<<< HEAD
package team.sxcoding.Config;

public enum ResponseStatus {

    SUCCESS(200,"SUCCESS"),

    NEED_LOGIN(401,"NEED_LOGIN"),

    FORBIDDEN(403,"PERMISSION_DENIED"),

    ERROR(404,"ERROR");


    private final int code;
    private final String desc;


    ResponseStatus(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getStatus(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
=======
package team.sxcoding.Config;

public enum ResponseStatus {

    SUCCESS(200,"SUCCESS"),

    NEED_LOGIN(401,"NEED_LOGIN"),

    FORBIDDEN(403,"PERMISSION_DENIED"),

    ERROR(404,"ERROR");


    private final int code;
    private final String desc;


    ResponseStatus(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getStatus(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
>>>>>>> 0be10400409c0210cb86ebb1866173f09c6b461e
