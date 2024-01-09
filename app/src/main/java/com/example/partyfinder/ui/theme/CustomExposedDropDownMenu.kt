package com.example.partyfinder.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExposedDropDownMenu(
    modifier: Modifier=Modifier,
    placeholder:String,
    isDropDownExpanded:Boolean,
    onExpandChange:(newValue:Boolean)->Unit,
    onValueChange:(newValue:String)->Unit,
    DropDownSelectedValue:String,
    onDismissRequest:()->Unit,
    exposedMenuContent:@Composable ColumnScope.()->Unit
){
    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        expanded = isDropDownExpanded,
        onExpandedChange =onExpandChange,
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = DropDownSelectedValue,
            onValueChange =onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.neutral_10),
                unfocusedTextColor = colorResource(id = R.color.primary),
                focusedTextColor = colorResource(id = R.color.primary),
                unfocusedPlaceholderColor = colorResource(id = R.color.primary),
                focusedPlaceholderColor = colorResource(id = R.color.primary),
                focusedLabelColor = colorResource(id = R.color.primary),
                focusedIndicatorColor = colorResource(id = R.color.primary),
                unfocusedIndicatorColor = colorResource(id = R.color.primary),
                focusedTrailingIconColor = colorResource(id = R.color.primary),
                unfocusedTrailingIconColor = colorResource(id = R.color.primary)
            ),
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropDownExpanded)
            },
            placeholder = { Text(text = placeholder) }
        )
        Box(modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxWidth(1f)
             ){

            ExposedDropdownMenu(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.neutral_10))
                    .width(IntrinsicSize.Max),
                expanded =isDropDownExpanded,
                onDismissRequest = onDismissRequest,
                content = exposedMenuContent)
        }
    }
}
//
//@Preview
//@Composable
//fun PreviewCustomExposedDropDownMenu(){
//    PartyFinderTheme {
//        CustomExposedDropDownMenu(
//            DropDownSelectedValue = "valorant",
//            isDropDownExpanded = true,
//            onExpandChange = {},
//            menuItemList = datasource.FindPartyGamesMenuItems,
//            onDismissRequest = {},
//            viewModel = viewModel()
//        )
//    }
//}