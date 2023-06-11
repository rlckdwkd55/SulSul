const Product = (props) => {
  return(
    <div>
      <div>
        <img src={props.imgPath} alt={props.prdName} width="200" height="250"/>
      </div>
      <div>
        <span>{props.prdName}</span>
        <span>{props.prdPrice} 원</span>
      </div>
      <div>
        <span>{props.rating}</span>
        <span>후기 {props.reviewCnt}</span>
      </div>
    </div>
  )
}

export default Product;