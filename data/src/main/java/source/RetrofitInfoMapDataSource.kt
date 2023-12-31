package source

import com.example.moduledb.controlDB.data.models.MDbPOIsResponse
import com.example.moduledb.controlDB.data.models.MDbPORechargeResponse
import com.example.moduledb.data.server.ServiceApi
import com.example.moduledb.data.server.request.POIsRequest
import com.example.moduledb.data.server.request.RechargingPointsRequest
import com.example.moduledb.utils.NetResult
import com.example.moduledb.utils.parse
import com.example.moduledb.utils.toNetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetrofitInfoMapDataSource @Inject constructor(private val serviceApi: ServiceApi) :
    InfoMapRemoteDataSource {


    override suspend fun getPointsInterest(): Flow<NetResult<ArrayList<MDbPOIsResponse>>> =
        flow {
            emit(serviceApi.getPOIs(POIsRequest()))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res -> res.parse {
                it.result!!.pointOfInterestList!! } }
            .flowOn(Dispatchers.IO)

    override suspend fun getPointsRecharge(): Flow<NetResult<ArrayList<MDbPORechargeResponse>>> =
        flow {
            emit(serviceApi.getRechargingPoints(RechargingPointsRequest()))
        }.catch { error ->
            emit(error.toNetworkResult())
        }
            .map { res -> res.parse {
                it.result!!.pointOfRechargeList!! } }
            .flowOn(Dispatchers.IO)
}