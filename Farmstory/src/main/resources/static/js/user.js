// 데이터 검증에 사용하는 정규표현식
let reUid   = /^[a-z]+[a-z0-9]{5,19}$/g;
let rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
let reName  = /^[ㄱ-힣]+$/;
let reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

// 폼 데이터 검증 결과 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isNickOk  = false;
let isEmailOk = false;
let isEmailAuthOk = false;
let isHpOk    = false;

$(function(){
    console.log('.............');

    // 아이디 검사
    $('input[name=uid]').keydown(function(){
        isUidOk = false;
    });

    $('#btnCheckUid').click(function(){
        console.log('btnIdCheck');
        let uid = $('input[name=uid]').val();

        if(isUidOk){
            return;
        }

        if(!uid.match(reUid)){
            $('.resultUid').css('color', 'red').text('유효한 아이디가 아닙니다.');
            isUidOk = false;
            return;
        }

        let jsonData = {"uid":uid};

        console.log('uid : '+uid);

        $('.resultUid').css('color', 'black').text('...');

        setTimeout(function(){
            console.log('here1');
            $.ajax({
                url:'/Farmstory/user/checkUid',
                method:'GET',
                data:jsonData,
                dataType:'json',
                success:function(data){
                console.log('here2');
                    if(data.result == 0){
                    console.log('here3');
                        $('.resultUid').css('color', 'green').text('사용 가능한 아이디 입니다.');
                        isUidOk = true;
                    }else{
                        console.log('here4');
                        $('.resultUid').css('color', 'red').text('이미 사용중인 아이디 입니다.');
                        isUidOk = false;
                    }
                }
            });
        }, 500);
    });

    // 비밀번호 검사
    $('input[name=pass2]').focusout(function(){
        let pass1 = $('input[name=pass1]').val();
        let pass2 = $('input[name=pass2]').val();

        if(pass2.match(rePass)){
            if(pass1 == pass2){
                isPassOk = true;
                $('.passResult').css('color', 'green').text('사용하실 수 있는 비밀번호 입니다..');
            }else{
                isPassOk = false;
                $('.passResult').css('color', 'red').text('비밀번호가 일치하지 않습니다..');
            }
        }else{
            isPassOk = false;
            $('.passResult').css('color', 'red').text('숫자,영문,특수문자 포함 5자리 이상이어야 합니다.');
        }
    });

    // 별명 검사
    $('input[name=nick]').keydown(function(){
    		isNickOk = false;
    });

    $('#btnCheckNick').click(function(){

        let nick = $('input[name=nick]').val();

        if(isNickOk){
            return;
        }

        let jsonData = {"nick":nick};

        $('.resultNick').css('color', 'black').text('...');

        $.ajax({
            url:'/Farmstory/user/checkNick',
            method:'GET',
            data:jsonData,
            dataType:'json',
            success:function(data){
                if(data.result == 0){
                    $('.resultNick').css('color', 'green').text('사용 가능한 닉네임 입니다.');
                    isNickOk = true;
                }else{
                    $('.resultNick').css('color', 'red').text('이미 사용중인 닉네임 입니다.');
                    isNickOk = false;
                }
            }
        });
    });

    // 이메일 검사하기
    $('input[name=email]').focusout(function(){

        let email = $(this).val();

        if(email.match(reEmail)){
            isEmailOk = true;
            $('.emailResult').text('');
        }else{
            isEmailOk = false;
            $('.emailResult').css('color', 'red').text('유효하지 않은 이메일 입니다.');
        }

    });

    // 이메일 인증 검사
    let isEmailCheck = false;
    let emailcode = 0;

    $('#btnEmailAuth').click(function(){
        let email = $('input[name=email]').val();

        if(email == ''){
            alert('이메일을 입력하세요.');
            return;
        }

        if(isEmailCheck){
            return;
        }

        isEmailCheck = true;
        $('.resultEmail').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');

        console.log("here1");
        $.ajax({
            url:'/Farmstory/user/emailAuth',
            method:'get',
                data:{"email":email},
                dataType:'json',
                success: function(data){
                    console.log("here2 : "+data);
                    if(data.status == 1){
                        // 메일발송 성공
                        emailcode = data.code;
                        $('.resultEmail').text('인증코드를 전송했습니다. 이메일을 확인하세요.');
                        $('.auth').show();

                    }else{
                        // 메일발송 실패
                        $('.resultEmail').text('인증코드 전송이 실패했습니다. 이메일을 확인 후 다시 시도하세요.');
                    }
                }
        });
    });

    $('btnEmailConfirm').click(function(){
        let code = $('input[name=auth]').val();

        if(code == emailcode){
            isEmailAuthOk = true;
            $('.resultEmail').css('color', 'green').text('이메일이 인증되었습니다.');
        }
    });
});