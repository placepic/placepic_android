package place.pic.ui.search.place

//서버로부터 받아와서 리스트에 출력할 데이터만 저장하는 Data class
data class PlaceSearchData (
    val placeName: String,
    val placeLocation: String,
    val alreadyIn: Boolean
)