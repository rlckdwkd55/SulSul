import {Link} from 'react-router-dom';

const Main = () => {
    return(
        <div className="container" id="main-wrapper">
        <div id="banner">

        </div>
        <div className="item-wrapper" id="bestItems-wrapper">
            <div className="wrapper-header">
                <span className="wrapper-header leftItem">BEST PICK! &#x1F44D</span>
                <span className="wrapper-header rightItem">더보기</span>
                <i className="fa-solid fa-chevron-right wrapper-header rightItem"></i>
            </div>
        </div>
        <div className="item-wrapper">
            <div className="wrapper-header">
                <span className="wrapper-header leftItem">NEW! &#x1F4AB</span>
                <span className="wrapper-header rightItem">더보기</span>
                <i className="fa-solid fa-chevron-right wrapper-header rightItem"></i>
            </div>
            <div id="newItems wrapper">
                <div className="newItems table">
                    <div className="newItems newItem-content">
                        <img src="/images/product/product01.jpg" alt="안동소주" width="200" height="250"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    );
}

export default Main