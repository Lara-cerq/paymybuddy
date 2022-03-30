package com.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.paymybuddy.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

}
