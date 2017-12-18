//
//  LoginViewController.swift
//  XplatformDemo
//
//  Created by Morten Hedengran on 11/12/2017.
//  Copyright © 2017 Trifork. All rights reserved.
//

import Foundation
import UIKit
import SnapKit

class LoginViewController: UIViewController {
    
    let usernameField = UITextField()
    let passwordField = UITextField()
    let loginButton = UIButton()
    let createUserButton = UIButton()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .lightGray
        usernameField.backgroundColor = .white
        usernameField.placeholder = "username"
        passwordField.backgroundColor = .white
        passwordField.placeholder = "password"
        
        loginButton.setTitle("login", for: .normal)
        loginButton.addTarget(self, action: #selector(login), for: .touchUpInside)
        
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
            make.top.equalTo(usernameField.snp.bottom).offset(15)
            make.left.right.height.equalTo(usernameField)
        }
        
        view.addSubview(loginButton)
        loginButton.snp.makeConstraints { (make) in
            make.top.equalTo(passwordField.snp.bottom).offset(30)
            make.left.right.height.equalTo(usernameField)
            make.height.equalTo(38)
        }
        
        view.addSubview(createUserButton)
        createUserButton.snp.makeConstraints { (make) in
            make.top.equalTo(loginButton.snp.bottom).offset(15)
            make.left.right.height.equalTo(usernameField)
            make.height.equalTo(38)
        }
    }
    
    @objc func login() {
        self.navigationController?.show(ShowDataViewController(), sender: self)
    }
    
    @objc func createUser() {
        self.navigationController?.show(CreateUserViewController(), sender: self)
    }
    
}
