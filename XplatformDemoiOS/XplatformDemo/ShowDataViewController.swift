//
//  ShowDataViewController.swift
//  XplatformDemo
//
//  Created by Morten Hedengran on 11/12/2017.
//  Copyright © 2017 Trifork. All rights reserved.
//

import Foundation
import UIKit

class ShowDataViewController: UIViewController {
    
    let dataLabel = UILabel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .white
        
        
        //must include the .h file for jsonArray if i want to use it in the code
        dataLabel.numberOfLines = 0
        dataLabel.font = UIFont.systemFont(ofSize: 17)
        let backend: J2ONetworkingHelper! = create_J2ONetworkingHelper_init()
        if let data = backend.getData() {
            var jsonString = ""
            for i in 0...data.length()-1 {
                if let obj = data.getJSONObject(with: i),
                    let username = obj.getStringWith("username"),
                    let password = obj.getStringWith("password") {
                    jsonString.append("Username:\(username) Password:\(password)\n")
                }
            }
            dataLabel.text = jsonString
        } else {
            dataLabel.text = "FEJL"
        }
        
        view.addSubview(dataLabel)
        dataLabel.snp.makeConstraints { (make) in
            make.center.equalTo(view)
            make.width.equalTo(view).offset(-60)
        }
        
    }
}
