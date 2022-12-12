
### Requirements
For building and running the application you need:

- JDK 1.11
- Maven 3
- MySQL

Update the database configurations on ``src/main/resources/application.properties`` 

### Running the application

Install the IDE (preferred IDE: IntelliJ IDEA) & run the main method from ``src/main/java/com/goodreadsbackend/api/ApiApplication.java`` <br/>
Alternatively, you can also run the following command from the terminal.

```shell
mvn spring-boot:run
```
The application is accessible on ``localhost:9999``

## insert new data first for test login

INSERT INTO datahub.usr_login(
usr_id_num, login_pwd, login_enable, acc_token)
VALUES ('super3', '', '1', '91210cdc613cc4338c0d81fde63f9e00cb7ee9575b7e85ce692ccec5f967b10d38069377e62dce25a60a0f1ca2e24b11b61cf7a899fb139a1b8e487c77d12b9f16462542'
);

INSERT INTO datahub.usr_data(
usr_id_num, crt_dttm, disp_nam_txt, email_addr, tel_num, acct_st_txt, upd_dttm)
VALUES ('super3', '2021-02-24', 'Super User', 'super@gmail.com', '123456', 'ACTIVE', '2022-12-11'
);
