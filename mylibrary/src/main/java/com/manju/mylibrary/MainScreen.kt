package com.manju.mylibrary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manju.mylibrary.data.IpInfo
import com.manju.mylibrary.uistate.FindMyIpViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen() {
    val viewModel: FindMyIpViewModel = hiltViewModel()
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is FindMyIpViewModel.UIEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter){
            FindMyIpLayout(state.ipInfoItems)
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
@Composable
fun FindMyIpLayout(ipInfo: IpInfo?) {
    Column {

        ipInfo?.ip?.let { RowItems("IP", it) }
        ipInfo?.country?.let { RowItems("Country",it) }
        ipInfo?.network?.let { RowItems("Network", it) }
        ipInfo?.version?.let { RowItems("Version", it) }
        ipInfo?.city?.let { RowItems("City", it) }
        ipInfo?.region?.let { RowItems("Region", it) }
    }

}

@Composable
fun RowItems(name: String, value: String) {
    Row (modifier = Modifier.padding(10.dp)){

        Text(
            modifier = Modifier.width(100.dp),
            fontWeight = FontWeight.Bold,
            text = name
        )

        Text(
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            text = value
        )
    }
}