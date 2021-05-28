import React from "react";

const PortfolioFilter = ({ categories, category, classes }) => {

  return (
    <div className="row mt-50">
      <div className="col-md-12">
        <div
          id="portfolio-gallery-filter"
          className={"cbp-l-filters-alignCenter text-sm-center " + classes}
        >
          <div
            data-filter="*"
            className="cbp-filter-item-active cbp-filter-item dark"
          >
            All
        </div>
          {categories.map((item, i) => (
            <div
              key={i}
              data-filter={`.${item}`}
              className="cbp-filter-item"
            >
              {item}
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default PortfolioFilter;
