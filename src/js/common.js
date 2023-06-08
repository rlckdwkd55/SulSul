export function getNoticeText(id) {
    const noticeText = {
        userId: '아이디는 6자 이상 입력해 주세요.',
        userEmail: '이메일은 필수 입력입니다.',
        checkId: '아이디 중복확인을 해주세요.',
        checkEmail: '이메일 중복확인을 해주세요.',
        userPwd: '비밀번호는 최소 8자 이상, 하나 이상의 문자, 숫자, 특수문자가 포함 되어야합니다.\n'
                + '특수문자: @$!%*#?&',
        userPwdCheck: '비밀번호가 일치하지 않습니다.',
        postNo: '우편번호를 입력해 주세요.',
        addr: '주소를 입력해 주세요.',
        addrDetail: '상세주소를 입력해 주세요.'
    }

    return noticeText[id];
}

// function validateInfo(id, isCorrect) {
//     const newNoticeInput = {...noticeInput};
//     let inputVal = input[id];
//     let noticeText = common.getNoticeText(id);
//     const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

//     if (isCorrect) {
//         setNoticeInput({ ...noticeInput, [id]: '' });
//         return true;
//     } else if (id === 'userId' && (inputVal.length < 6 || !inputVal)) {
//         setNoticeInput({ ...noticeInput, [id]: noticeText });
//         return false;
//     } else if (id === 'userPwd' && !reg.test(inputVal)) {
//         setNoticeInput({ ...noticeInput, [id]: noticeText });
//         return false;
//     } else if (id === 'userPwdCheck') {
//         const pwd = input.userPwd;
//         const pwd2 = input[id];

//         if (pwd !== pwd2) {
//             setNoticeInput({ ...noticeInput, [id]: noticeText });
//             return false;
//         } else {
//             setNoticeInput({ ...noticeInput, [id]: '' });
//             return true;
//         }

//     } else if (inputVal.length === 0 || !inputVal) {
//         if (/postNo|addr|addrDetail/.test(id)) id = 'addr';
//         setNoticeInput({ ...noticeInput, [id]: noticeText });
//         return false;
//     } else {
//         setNoticeInput({ ...noticeInput, [id]: '' });
//         return true;
//     }
// }