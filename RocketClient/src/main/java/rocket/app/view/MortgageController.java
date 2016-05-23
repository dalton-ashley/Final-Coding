package rocket.app.view;


import java.text.DecimalFormat;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {
	@FXML
		TextField txtIncome;
	@FXML
		TextField txtExpenses;
	@FXML
		TextField txtCreditScore;
	@FXML
		TextField txtHouseCost;
	@FXML
		ComboBox<String> cmbterm;
	@FXML
		Label lblMortgagePayment;
	
	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	public void initialize(){
		ObservableList<String> cb = FXCollections.observableArrayList("15 Years", "30 Years"); 
	 	cmbterm.setItems(cb); 
	}
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		a.setLoanRequest(lq);
		
		double Income = Double.parseDouble(txtIncome.getText()); 
		 	lq.setIncome(Income);
		 	System.out.println(Income); 
		double Expenses = Double.parseDouble(txtExpenses.getText());
			lq.setExpenses(Expenses);
			System.out.println(Expenses);
		int CreditScore = (int)Double.parseDouble(txtCreditScore.getText());
			lq.setiCreditScore(CreditScore);
			System.out.println(CreditScore);
		double HouseCost = Double.parseDouble(txtHouseCost.getText());
			lq.setdAmount(HouseCost);
			
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		double payment; 
		payment = lRequest.getdPayment(); 
		 
		 
	double PITI1 = (0.28*lRequest.getIncome()/12); 
	double PITI2 = (0.36*lRequest.getIncome()/12) - lRequest.getExpenses(); 
	if (payment >=PITI1 & payment >=PITI2){ 
		DecimalFormat decimal = new DecimalFormat("#,###.##");
		String monthly =  "Monthly Payment:  $"+ decimal.format(payment); 
		System.out.println(monthly);  
		lblMortgagePayment.setText(monthly); 
	} 
	else{ 
		lblMortgagePayment.setText("You Cannot Afford This House"); 
	}
  }
}
