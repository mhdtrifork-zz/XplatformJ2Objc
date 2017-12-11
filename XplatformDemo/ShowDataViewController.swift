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
        
        dataLabel.numberOfLines = 0
        dataLabel.font = UIFont.systemFont(ofSize: 17)
        dataLabel.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
        
        view.addSubview(dataLabel)
        dataLabel.snp.makeConstraints { (make) in
            make.center.equalTo(view)
            make.width.equalTo(view).offset(-60)
        }
        
    }
}
