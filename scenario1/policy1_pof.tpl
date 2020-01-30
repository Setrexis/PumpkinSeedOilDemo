accept(Form) :-
  extract(Form, format, pumpkinSeedOil),

  extract(Form, purchaser, Buyer),
  extract(Buyer, name, BuyerName),
  print(BuyerName),

  authorize_order(Form),

  extract(Form, certificate, Certificate),
  extract(Certificate, format, x509cert),
  extract(Certificate, pubKey, PK),
  
  verify_signature(Form, PK),
  check_qualified(Certificate).


authorize_order(Form) :-
  extract(Form, item_id, ID),
   ID == 54678,
  extract(Form, amount, Amount),
   Amount <= 10,
  print(authorized_Order_for_Item_54678).

authorize_order(Form) :-
  extract(Form, item_id, ID),
   ID == 42,
  extract(Form, amount, Amount),
   Amount <= 10,
  print(authorized_Order_for_Item_42).

authorize_order(Form) :-
  extract(Form, item_id, ID),
   ID == 7,
  extract(Form, amount, Amount),
   Amount <= 100,
  print(authorized_Order_for_Item_7).

authorize_order(Form) :-
  print(authorizing_order_failed),
  false().
  

check_qualified(Certificate) :-
  extract(Certificate, issuer, IssuerCertificate),

  trustschemeX(IssuerCertificate, pumpkin_Oil_Federation, TrustListEntry),
  extract(TrustListEntry, format, trustlist_entry),

  extract(TrustListEntry, pubKey, PkIss),
  verify_signature(Certificate, PkIss).


trustschemeX(IssuerCert, TrustedScheme, TrustListEntry) :-
  extract(IssuerCert, trustScheme, Claim),
  trustscheme(Claim, TrustedScheme),
  trustlist(Claim, IssuerCert, TrustListEntry).

