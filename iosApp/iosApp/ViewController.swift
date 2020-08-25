import UIKit
import app

class ViewController: UIViewController, LoginScreenContractView {
    
    func openMainScreen() {
        password.text = "login success"
    }
    
    var presenter = DiKt.provideLoginScreenPresenter()

    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.attachView(view: self)

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBOutlet weak var login: UITextField!
    
    @IBOutlet weak var password: UITextField!
    
    
    @IBAction func onSignInClicked(_ sender: UIButton) {
        
        presenter.onLoginClicked(login: login.text ?? "", password: password.text ?? "")
    }
}
