package com.blundell.tests

import com.blundell.tests.MyActivity
import com.blundell.tests.shadow.MyActivityManagerShadow
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.robospock.RoboSpecification

@Config(shadows = [MyActivityManagerShadow], manifest = "src/main/AndroidManifest.xml")
class ShadowSpec extends RoboSpecification {

    def "shouldCompile"() {
        given:
        def mainActivity = Robolectric.buildActivity(MyActivity).create().get()

        when:
        def text = mainActivity.my_hello_text_view.getText()

        then:
        text == "Hello world!"
    }
}