package example.mapper;

import example.model.BankAccountInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements RowMapper<BankAccountInfo> {

public static final String BASE_SQL = "select BANK_ACCOUNT.ID, BANK_ACCOUNT.FULL_NAME, BANK_ACCOUNT.BALANCE from BANK_ACCOUNT";


    @Override
    public BankAccountInfo mapRow(ResultSet resultSet, int i) throws SQLException
    {
        Long id = resultSet.getLong("ID");
        String fullName = resultSet.getString("FULL_NAME");
        double balance = resultSet.getDouble("BALANCE");

        return new BankAccountInfo(id, fullName, balance);
    }
}
