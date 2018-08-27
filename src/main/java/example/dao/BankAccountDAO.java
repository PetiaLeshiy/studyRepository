package example.dao;


import example.exception.BankTransactionExeption;
import example.mapper.BankAccountMapper;
import example.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class BankAccountDAO extends JdbcDaoSupport {


    @Autowired
    public BankAccountDAO (DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<BankAccountInfo> getBankAccounts(){
        String sql = BankAccountMapper.BASE_SQL;
        Object[] params = new Object[]{};
        BankAccountMapper mapper = new BankAccountMapper();
        List<BankAccountInfo> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }
    public BankAccountInfo findBankAccount(Long id) {
        String sql = BankAccountMapper.BASE_SQL + " WHERE BANK_Account.ID = ?";
        Object[] params = new Object[]{id};
        BankAccountMapper mapper = new BankAccountMapper();
        try {
            BankAccountInfo bankAccount = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return bankAccount;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional(propagation =  Propagation.MANDATORY)
    public void addAmount (Long id, double amount) throws BankTransactionExeption{
        BankAccountInfo accountInfo = this.findBankAccount(id);
        if (accountInfo == null) {
            throw new BankTransactionExeption("Account not found" + id);
            }
            double newBalance = accountInfo.getBalance()+amount;
        if (accountInfo.getBalance() + amount < 0){
            throw new BankTransactionExeption("The money in the account '" + id + "' is not enough (" + accountInfo.getBalance() + ")");
        }
        accountInfo.setBalance(newBalance);

        String sqlUpdate = "update BANK_ACCOUNT set Balance = ? where id = ?";
        this.getJdbcTemplate().update(sqlUpdate, accountInfo.getBalance(), accountInfo.getId());

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionExeption.class)
    public void sendMoney(Long fromAccountID, Long toAccountID, double amount)throws  BankTransactionExeption {
        addAmount(toAccountID, amount);
        addAmount(fromAccountID, -amount);
    }

}
