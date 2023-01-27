$(function(){
    // 댓글 삭제
    $(document).on('click', '.remove', function(e){
        e.preventDefault();

        let isDeleteOk = confirm('정말 삭제하시겠습니까?');

        if(isDeleteOk){
            let article = $(this).closest('article');
            let no = $(this).attr('data-no');
            let parent = $(this).attr('data-parent');

            console.log('here3');

            let jsonData = {"no":no, "parent":parent};

            $.ajax({
                url:'/Farmstory/board/commentDelete',
                method:'GET',
                data:jsonData,
                dataType:'json',
                success:function(data){
                    if(data.result){
                        alert('댓글이 삭제되었습니다.');
                        article.hide();
                    }
                }
            });
        }
    });

    // 댓글 작성
    $('.commentForm > form').submit(function(){
        let no = $(this).children('input[name=no]').val();
        let uid = $(this).children('input[name=uid]').val();
        let regip = $(this).children('input[name=regip]').val();
        let content = $(this).children('textarea[name=content]').val();

        jsonData = {
            "no":no,
            "uid":uid,
            "regip":regip,
            "content":content
        };

        $.ajax({
            url:'/Farmstory/board/commentWrite',
            method:'post',
            data:jsonData,
            dataType:'json',
            success:function(data){
                if(data.result > 0){
                    let comment = "<article>";
                        comment += "<span class='nick'>"+data.nick+"</span>";
                        comment += "<span class='date'>"+data.date+"</span>";
                        comment += "<p class='content'>"+data.content+"</p>";
                        comment += "<div>";
                        comment += "<a href='#' class='remove' data-no='"+data.no+"' data-parent='"+data.parent+"'>삭제</a>&nbsp";
                        comment += "<a href='#' class='modify' data-no='"+data.no+"'>수정</a>";
                        comment += "</div>";
                        comment += "</article>";

                    $('.commentList > .empty').hide();
                    $('.commentList').append(comment);
                    textarea.val('');
                }
            }
        });

        return false;
    });
});