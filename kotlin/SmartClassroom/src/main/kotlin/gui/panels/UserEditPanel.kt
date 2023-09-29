package gui.panels

import auxiliary.Labels

class UserEditPanel(private val context: Context): BasePanel(context) {
    override val titleText : String = Labels[Labels.USER_EDIT].description

}