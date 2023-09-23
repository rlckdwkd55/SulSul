import { useDaumPostcodePopup } from 'react-daum-postcode';
import BtnRed from '../atoms/btnRed';

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
            address: {
              ...prev.address,
              postNo: postNo,
              address: fullAddress
            }
        }));
      };
    
      const handleClick = () => {
        open({ onComplete: handleComplete });
      };

    return (
        <BtnRed type='button' className="btn-join" name='우편번호' clickEvent={handleClick}></BtnRed>
    )
}

export default DaumPostPopup