package com.app.entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "customer")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Customer extends AppUser implements Serializable {

	private static final long serialVersionUID = -8309072671259373208L;

	@Column(nullable = false)
	private String customerCode;

	@Column
	private String billNumber;

	@Column(nullable = false)
	private Double discount = 0.0;

	@Column(nullable = false)
	private Double payableAmount = 0.0;

	@Column(nullable = false)
	private String payableCurrency = "Rupee";

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Subscription> subscriptions = new LinkedHashSet<Subscription>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<BankAccountDetails> bankAccountDetails = new LinkedHashSet<BankAccountDetails>();

	public boolean addSubscription(Subscription subscription) {
		return subscriptions.add(subscription);
	}

	public boolean removeSubscription(Subscription subscription) {
		return subscriptions.remove(subscription);
	}

	public boolean addBankAccountDetails(BankAccountDetails bankAccount) {
		return bankAccountDetails.add(bankAccount);
	}

	public boolean removeBankAccountDetails(BankAccountDetails bankAccount) {
		return bankAccountDetails.remove(bankAccount);
	}
}
