import { useDaumPostcodePopup } from 'react-daum-postcode';

const DaumPostPopup = (props) => {
    const open = useDaumPostcodePopup();

    const handleComplete = (data) => {
        let fullAddress = data.address;
        let postNo = data.zonecode;
        let extraAddress = "";
    
        if (data.addressType === "R") {
          if (data.bname !== "") {
            extraAddress += data.bname;
          }
          if (data.buildingName !== "") {
            extraAddress += extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
          }
          fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
        }
    
        //주소값을 상태값으로..
        props.setInput((prev) => ({
            ...prev,
            postNo: postNo,
            addr: fullAddress
        }));
      };
    
      const handleClick = (e) => {
        open({ onComplete: handleComplete });
        e.preventDefault();
      };

    return (
        <button type='button' className="btn-join" name='searchAddr' onClick={handleClick}>우편번호</button>
    )
}

export default DaumPostPopup