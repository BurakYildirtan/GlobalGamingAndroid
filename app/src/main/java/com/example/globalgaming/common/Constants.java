package com.example.globalgaming.common;

public class Constants {

    //connection
    public static final String BASE_URL = "http://141.87.68.58:4567/";

    //userRepo
    public static final String USER_REGISTRATION = "register";

    public static final String USER_LOGIN = "userLogin";
    public static final String USER_UPDATE = "updateUser";

    public static final String USER_GET = "userById";


    //productRepo
    public static final String PRODUCT_ALL = "productsAll";
    public static final String PRODUCT_ADD = "addProduct";

    public static final String PRODUCT_UPDATE = "updateProduct";

    public static final String DELETE_PRODUCT = "deleteProduct";


    //hardwareRepo
    public static final String HARDWARE_ALL = "hardwareAll";

    public static final String HARDWARE_MIN_REQUIREMENTS = "min";

    public static final String HARDWARE_REC_REQUIREMENTS = "rec";

    //softwareRepo
    public static final String SOFTWARE_ALL = "softwareAll";


    //saleRepo
    public static final String SALE_ALL= "sales";

    public static final String TAG_LOGIN = "Login";
    public static final String TAG_REGISTRATION = "Registration";
    public static final String TAG_MAIN = "Main";

    //UserModel
    public static final String USER_MODEL_ID = "userId";
    public static final String USER_MODEL_EMAIL = "email";
    public static final String USER_MODEL_ROLE = "role";
    public static final String USER_MODEL_PASSWORD = "password";
    public static final String USER_MODEL_BIRTHDAY = "bday";
    public static final String USER_MODEL_STREET = "street";
    public static final String USER_MODEL_POSTAL_CODE = "pcode";
    public static final String USER_MODEL_CITY = "city";
    public static final String USER_MODEL_USER_NAME = "userName";

    //ProductType
    public static final int PRODUCT_TYPE_SOFTWARE = 1;
    public static final int PRODUCT_TYPE_HARDWARE = 2;

    //Product-Model
    public static final String PRODUCT_MODEL_PRODUCT_ID = "productId";

    public static final String PRODUCT_MODEL_PRODUCT_TYPE = "product_type";
    public static final String PRODUCT_MODEL_DESIGNATION = "designation";
    public static final String PRODUCT_MODEL_PRICE = "price";
    public static final String PRODUCT_MODEL_SALE_IN_PERCENT = "saleInPercent";
    public static final String PRODUCT_MODEL_RATING = "rating";
    public static final String PRODUCT_MODEL_PIC_PATH = "picPath";
    public static final String PRODUCT_MODEL_RELEASE_DATE = "releaseDate";

    //Software-Model
    public static  final String SOFTWARE_MODEL_FSK = "fsk";
    public static  final String SOFTWARE_MODEL_GENRE = "genre";

    //Hardware-Model
    public static  final String HARDWARE_MODEL_TYPE = "type";
    public static  final String HARDWARE_MODEL_MANUFACTURER = "manufacturer";


    //other

    public static final String TITLE_KEY_SALE = "sale";
    public static final String TITLE_KEY_SOFTWARE = "software";
    public static final String TITLE_KEY_HARDWARE = "hardware";

    public static final String ROLE_ADMIN = "admin";

    public static final int BTN_ADD = 1;
    public static final int BTN_REMOVE = 0;



}
