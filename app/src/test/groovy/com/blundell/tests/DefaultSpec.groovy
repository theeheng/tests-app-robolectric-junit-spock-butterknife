package com.blundell.tests

import android.widget.Button
import com.blundell.tests.MyActivity
import com.blundell.tests.shadow.MyActivityManagerShadow
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.robospock.GradleRoboSpecification

@Config(shadows = [MyActivityManagerShadow], constants = BuildConfig)
class DefaultSpec extends GradleRoboSpecification {

    MyActivity myActivity

    def "setup"(){
        Robolectric
        myActivity = Robolectric.buildActivity(MyActivity).create().get()
    }

    def "should inflate textviews"(){
        expect:
        myActivity.my_hello_text_view.getText() == "Hello world!"
    }

    def "should inflate button"(){
        expect:
        myActivity.button.getText() == "New Button"
    }

    def "should change text when button is clicked"(){
        given:
        def b = (Button) myActivity.findViewById(R.id.button);
        when:
        b.callOnClick()
        then:
        myActivity.my_hello_text_view.getText() == "Button clicked !"
    }
}
