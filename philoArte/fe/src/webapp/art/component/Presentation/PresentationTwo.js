import React from "react";
import parse from "html-react-parser";
import { Link } from "react-router-dom";

const PresentationTwo = ({ title, text, textBtn, pathBtn, backfont }) => {

  const loginValue = JSON.parse(localStorage.getItem("artist"))

  const login = () => {
    return (
      loginValue ?
      <Link to={pathBtn} className="btn btn-lg btn-color btn-square">
        {textBtn || "Read More"}
      </Link>
     :
      alert('로그인을 해주세요 !')
      // window.location('/artist/artist_signin')
    )
  }
  
  return (
    <section
      style={{ background: `url() center center no-repeat #24182e` }}
      className=""
    >
      <div className="container">
        <div className="row">
          <div className="col-md-8 text-left white-color">
            <h1 className="font-700 xs-font-40px font-40px" data-backfont={backfont || "Art"}>
              {title && parse(title)}
            </h1>
            <p className="mt-30">{text && parse(text)}</p>
            <p className="mt-30">
              {
                loginValue ?
                  <Link to={pathBtn} className="btn btn-lg btn-color btn-square">
                    {textBtn || "Read More"}
                  </Link>
                  :
                  <Link to="/artist/artist_signin" className="btn btn-lg btn-color btn-square">
                    로그인
                  </Link>
              }
            </p>
          </div>
        </div>
      </div>
    </section>
  );
}

export default PresentationTwo;
