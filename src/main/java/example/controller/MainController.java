package example.controller;


import example.dao.BankAccountDAO;
import example.dao.VsemPoShapkeDAO;
import example.exception.BankTransactionExeption;
import example.form.SendMoneyForm;
import example.model.BankAccountInfo;
import example.model.VsemPoShapkeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController
{
    @Autowired
    private BankAccountDAO bankAccountDAO;


    @Autowired
    private VsemPoShapkeDAO vsemPoShapkeDAO;

    @RequestMapping (value = "/allShapka", method = RequestMethod.GET)
    public String showAllShapka (Model model) {
        List<VsemPoShapkeModel> list = vsemPoShapkeDAO.getVsemPoShapkeList();
        model.addAttribute("accountInfos", list);
        return  "allShapka";

    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccount (Model model){
        List<BankAccountInfo> list = bankAccountDAO.getBankAccounts();
        model.addAttribute("accountInfos", list);
        return "accountsPage";
    }

    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {
        SendMoneyForm sendMoneyForm = new SendMoneyForm(1L, 2L, 700d);
        model.addAttribute("sendMoneyForm", sendMoneyForm);
        return "sendMoneyPage";}

    @RequestMapping(value = "/sendMoney", method =  RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {
            System.out.println("SEND money::" + sendMoneyForm.getAmount());
            try {
                bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), sendMoneyForm.getToAccountId(), sendMoneyForm.getAmount());
            } catch (BankTransactionExeption bankTransactionExeption) {
                model.addAttribute("error Message", "Error: " + bankTransactionExeption.getMessage());
            return "/sendMoneyPage";
            }
            return "redirect:/";

        }



    }







