//
//  CreateUserViewController.swift
//  XplatformDemo
//
//  Created by Morten Hedengran on 11/12/2017.
//  Copyright © 2017 Trifork. All rights reserved.
//

import Foundation
import UIKit

class CreateUserViewController: UIViewController {
    let usernameField = UITextField()
    let passwordField = UITextField()
    let password2Field = UITextField()
    let errorLabel = UILabel()
    let createUserButton = UIButton()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .lightGray
        usernameField.backgroundColor = .white
        usernameField.placeholder = "username"
        passwordField.backgroundColor = .white
        passwordField.placeholder = "password"
        password2Field.backgroundColor = .white
        password2Field.placeholder = "password again"
        
        errorLabel.numberOfLines = 0
        errorLabel.font = UIFont.systemFont(ofSize: 17)
        errorLabel.textColor = .red
        errorLabel.text = "Error in password entry"
        
        createUserButton.setTitle("create user", for: .normal)
        createUserButton.addTarget(self, action: #selector(createUser), for: .touchUpInside)
        
        layoutViews()
    }
    
    func layoutViews() {
        view.addSubview(usernameField)
        usernameField.snp.makeConstraints { (make) in
            make.top.equalTo(view.snp.top).offset(175)
            make.left.equalTo(view).offset(30)
            make.right.equalTo(view).offset(-30)
            make.height.equalTo(38)
        }
        
        view.addSubview(passwordField)
        passwordField.snp.makeConstraints { (make) in
            make.top.equalTo(usernameField.snp.bottom).offset(30)
            make.left.right.height.equalTo(usernameField)
        }
        
        view.addSubview(password2Field)
        password2Field.snp.makeConstraints { (make) in
            make.top.equalTo(passwordField.snp.bottom).offset(15)
            make.left.right.height.equalTo(passwordField)
        }
        
        view.addSubview(errorLabel)
        errorLabel.snp.makeConstraints { (make) in
            make.top.equalTo(password2Field.snp.bottom).offset(5)
            make.left.right.height.equalTo(password2Field)
        }
        
        view.addSubview(createUserButton)
        createUserButton.snp.makeConstraints { (make) in
            make.top.equalTo(errorLabel.snp.bottom).offset(30)
            make.left.right.height.equalTo(usernameField)
            make.height.equalTo(38)
        }
    }

    @objc func createUser() {
        let username = usernameField.text ?? ""
        let password = passwordField.text ?? ""
        let password2 = password2Field.text ?? ""
        
        //You can specify a prefix instead of the packagename, in this case J2O
        //Or you can use @ObjectiveCName("func name") in the java file, like with stringVerification here
        let verify: StringVerification! = create_StringVerification_init()
        let usernameVerified: J2OVerified! = verify.verifyUsername(username)
        if usernameVerified.isValid() {
            let passwordVerified: J2OVerified! = verify.verifyPassword(password)
            if passwordVerified!.isValid() {
                //Class methods use c style
                let passwordCompare: J2OVerified! = StringVerification_compareString_withString_(password, password2)
                if passwordCompare.isValid() {
                    errorLabel.isHidden = true
                    //JavaLangBoolean doesnt play nicely with either swift or objc. (use primitive boolean instead)
                    let backend: J2ONetworkingHelper! = create_J2ONetworkingHelper_init()
                    if backend.createUser(with: username, with: password) {
                        errorLabel.isHidden = false
                        errorLabel.text = "Succes"
                    } else {
                        errorLabel.isHidden = false
                        errorLabel.text = "Der eksisterer allerede en bruger med dette navn"
                    }
                    self.navigationController?.popViewController(animated: true)
                } else {
                    errorLabel.text = passwordCompare?.errorMsg()
                }
            } else {
                errorLabel.text = passwordVerified?.errorMsg()
            }
        } else {
            errorLabel.text = usernameVerified?.errorMsg()
        }
    }
}
