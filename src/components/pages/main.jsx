import { useNavigate } from 'react-router-dom';
import ProductList from '../template/productList'

const ContentHead = (props) => {
    const navigate = useNavigate();
    return (
        <div className="wrapper-header">
            <span className="leftItem">{props.title}</span>
            <span className="rightItem" onClick={()=>{
                navigate('/product', {state: {cate: props.cate}});
            }}>더보기</span>
            <i className="fa-solid fa-chevron-right wrapper-header rightItem"></i>
        </div>
    )
}

const Main = () => {
    return(
        <div className="container" id="main-wrapper">
            <div id="banner">

            </div>
            <div className='bestItems-wrapper'>
                <ContentHead title={'BEST PICK!'} cate={'best'} />
            </div>
            <div className='newItems-wrapper'>
                <ContentHead title={'NEW!'} cate={'new'} />
                <ProductList key={'newItems'} />
            </div>
        </div>
    );
}

export default Main