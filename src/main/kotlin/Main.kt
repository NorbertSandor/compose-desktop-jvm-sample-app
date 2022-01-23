// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import arrow.optics.Optional
import arrow.optics.dsl.*
import com.example.domain.*

@Composable
@Preview
fun App() {
    var john by remember {
        mutableStateOf(
            Employee(
                "John Doe",
                Company("Kategory", Address("Functional city", Street(42, "lambda street")))
            )
        )
    }

    MaterialTheme {
        Column {
            Text(john.company?.address.toString())
            Button(onClick = {
                // Old way
//                john = john.copy(
//                    company = john.company?.copy(
//                        address = john.company!!.address.copy(
//                            street = john.company!!.address.street.copy(number = john.company!!.address.street.number + 1)
//                        )
//                    )
//                )

                // Arrow way
                val optional: Optional<Employee, Int> = Employee.company.address.street.number
                john = optional.modify(john) { it + 1 }
            }) {
                Text("Increment street number")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
