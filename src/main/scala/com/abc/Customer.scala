package com.abc

import java.util.Date

import scala.collection.mutable.ListBuffer

case class Customer(val name: String) {

  val accounts: ListBuffer[Account] = ListBuffer[Account]()

  def openAccount(account: Account): Customer = {
    accounts += account
    this
  }

  def numberOfAccounts: Int = accounts.size
  def totalInterestEarned: Double = accounts.map(_.interestEarned).sum

  def transfer(from: Account, to: Account, amount: Double, txDate: Date = DateUtils.now) = from.transferTo(to, amount, txDate)

  /**
   * This method gets a statement
   */
  def getStatement: String = {
    val totalAcrossAllAccounts = accounts.map(_.getBalance).sum
    val statement = f"Statement for $name\n" +
      accounts.map(_.statementForAccount).mkString("\n", "\n\n", "\n") +
      s"\nTotal In All Accounts ${FormatUtils.toDollars(totalAcrossAllAccounts)}"
    statement
  }

}

