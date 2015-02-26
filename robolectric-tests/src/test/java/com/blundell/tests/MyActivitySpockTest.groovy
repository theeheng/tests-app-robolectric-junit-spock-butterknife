package com.blundell.tests

import android.widget.Button;
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import pl.polidea.robospock.RoboSpecification

/**
 * Created by htan on 26/02/2015.
 */
@Config(manifest = "../app/src/main/AndroidManifest.xml")
public class MyActivitySpockTest extends RoboSpecification {

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
