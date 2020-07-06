import React, { useState, useEffect } from "react";
import MaterialTable from "material-table";
import axios from "axios";

export default function MaterialTableDemo() {
  const [data, setData] = useState([]);
  const [state] = React.useState({
    columns: [
      { title: "City", field: "city" },
      { title: "Street", field: "street" },
      { title: "Number", field: "number", type: "numeric" },
      { title: "Owner", field: "owner" },
      { title: "Size", field: "size", type: "numeric" },
      { title: "Market value", field: "marketValue", type: "numeric" },
      {
        title: "Property type",
        field: "propertyType",
        lookup: { HOUSE: "HOUSE", INDUSTRIAL: "INDUSTRIAL", APARTMENT: "APARTMENT"  },
      },
      {
        title: "Tax (year)",
        render: (rowData) => {
          try {
            let taxRate =
              (rowData.marketValue * 1.21 - rowData.marketValue) * 12;
            return taxRate;
          } catch (error) {
            return;
          }
        },
      },
    ],
  });

  // filters all iteams by itemId
  const deleteFromArray = (itemId) => {
    setData(data.filter(({ id }) => id !== itemId));
  };

  const axiosDeleteRecord = (id) => {
    axios
      .delete(`http://localhost:8080/api/${id}`)
      .then((response) => {
        console.log(`${id} id record deleted`);
        console.log(response.status);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const axiosPost = (newElement) => {
    axios
      .post("http://localhost:8080/api/", newElement)
      .then((response) => {
        if (response.data != null) {
          console.log(` New recod created`);
          console.log(response.status);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const axiosUpdateRecord = (id, updatedData) => {
    axios
      .put(`http://localhost:8080/api/${id}`, updatedData)
      .then((response) => {
        console.log(`${id} id record updated`);
        console.log(response.status);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/")
      .then((response) => {
        console.log(response.data);
        console.log(response.status);
        console.log(response.statusText);
        console.log(response.headers);
        console.log(response.config);
        setData(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <MaterialTable
      title="Real Estate Registry for Buildings"
      columns={state.columns}
      data={data}
      editable={{
        onRowAdd: (newData) =>
          new Promise((resolve) => {
            setTimeout(() => {
              resolve();
              setData((prevState) => [...prevState, newData]);
              console.log(newData);
              axiosPost(newData);
            }, 600);
          }),
        onRowUpdate: (newData, oldData) =>
          new Promise((resolve) => {
            const oldIndex = data.indexOf(oldData);
            const arrayId = oldData.id;
            setTimeout(() => {
              resolve();
              if (oldData) {
                console.log(`Old data index ${oldIndex}`);
                console.log(arrayId);
                setData((prevState) => {
                  prevState = prevState.filter(({ id }) => id !== arrayId);
                  return [...prevState, newData];
                });
                axiosUpdateRecord(arrayId, newData);
              }
            }, 600);
          }),
        onRowDelete: (oldData) =>
          new Promise((resolve) => {
            setTimeout(() => {
              resolve();
              console.log(oldData);
              const arrayId = oldData.id;
              console.log(arrayId);
              axiosDeleteRecord(arrayId);
              deleteFromArray(arrayId);
            }, 600);
          }),
      }}
    />
  );
}
