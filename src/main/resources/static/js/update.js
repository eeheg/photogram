// (1) 회원정보 수정
function update(userId, event) {
    event.preventDefault();  //form 태그 액션을 막음
    let data=$("#profileUpdate").serialize();  //form 데이터가 전부 담겨짐

    $.ajax({
        type:"put",
        url: `/api/user/${userId}`,
        data: data,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => {  //HttpStatus 상태코드: 200번대
        console.log("업데이트 성공", res);
        //location.href=`/user/${userId}`;
    }).fail(error => {
        console.log("error:", error);
        console.log("error.data: ", error.responseJSON.data);
        console.log("error.responseJSON: ", error.responseJSON);
        if(error.responseJSON.errorMap == null) {
            alert(error.responseJSON.errorMessage);
        } else {
            console.log("업데이트 실패", error.responseJSON);
            alert(JSON.stringify(error.responseJSON.data));
        }
    })
}