const fs = require("fs");
const bodyParser = require("body-parser");
const jsonServer = require("json-server");
const jwt = require("jsonwebtoken");

const server = jsonServer.create();

const router = jsonServer.router("./db.json");

const db = JSON.parse(fs.readFileSync("./db.json", "UTF-8"));

const middlewares = jsonServer.defaults();
const PORT = process.env.PORT || 3000;

server.use(middlewares);

server.use(jsonServer.defaults());
server.use(bodyParser.urlencoded({ extended: true }));
server.use(bodyParser.json());

const SECRET_KEY = "123456789";
const expiresIn = "1h";

function createToken(payload) {
  return jwt.sign(payload, SECRET_KEY, { expiresIn });
}

function verifyToken(token) {
  return jwt.verify(token, SECRET_KEY, (err, decode) =>
    decode !== undefined ? decode : err
  );
}

function isAuthenticated({ account, password }) {
  return (
    db.users.findIndex(
      (user) =>
        (user.email === account || user.phone === account) &&
        user.password === password
    ) !== -1
  );
}

server.post("/register", (req, res) => {
  const { name, phone, email, password, confirm_password } = req.body;

  exist_user = db.users.findIndex((x) => x.email === email || x.phone == phone);
  if (exist_user !== -1) {
    return res.status(401).json({
      status: 401,
      message: "Email or phone already in use!",
    });
  }

  if (password !== confirm_password) {
    return res.status(401).json({
      status: 401,
      message: "Confirm password does not match",
    });
  }

  const new_user = {
    id: db.users.length + 1,
    name,
    phone,
    email,
    password,
  };

  db.users.push(new_user);
  fs.writeFileSync("./db.json", JSON.stringify(db), () => {
    if (err) return console.log(err);
    console.log("writing to " + fileName);
  });
  res.status(201).json({
    status: 201,
    message: "Success",
    data: new_user,
  });
});

//login
server.post("/login", (req, res) => {
  const account = req.body.email || req.body.phone;
  const password = req.body.password;

  if (isAuthenticated({ account, password }) === false) {
    const status = 401;
    const message = "Incorrect email/phone or password";
    res.status(status).json({ status, message });
    return;
  }
  const access_token = createToken({ account, password });
  res.status(200).json({
    status: 200,
    message: "Success",
    data: {
      access_token,
    },
  });
});

server.use("/auth", (req, res, next) => {
  if (
    req.headers.authorization == undefined ||
    req.headers.authorization.split(" ")[0] !== "Bearer"
  ) {
    const status = 401;
    const message = "Bad authorization header";
    res.status(status).json({ status, message });
    return;
  }
  try {
    let verifyTokenResult;
    verifyTokenResult = verifyToken(req.headers.authorization.split(" ")[1]);

    if (verifyTokenResult instanceof Error) {
      const status = 401;
      const message = "Error: access_token is not valid";
      res.status(status).json({ status, message });
      return;
    }
    next();
  } catch (err) {
    const status = 401;
    const message = "Token đã hết hạn";
    res.status(status).json({ status, message });
  }
});

//Xem thông tin user
server.get("/auth/profile", (req, res) => {
  const decodeToken = jwt.decode(req.headers.authorization.split(" ")[1]);

  if (!decodeToken) {
    return res.status(401).json({
      status: 401,
      message: "Error: access_token is not valid",
    });
  }

  console.log(decodeToken);

  const result = db.users.find(
    (user) =>
      user.email == decodeToken.account || user.phone == decodeToken.account
  );

  const status = 200;
  return res.status(status).json({ status, result });
});

//delete user by email
server.delete("/auth/users/:email", (req, res) => {
  const email = req.params.email;

  const exist_email = db.users.findIndex((user) => user.email == email);
  if (exist_email !== -1) {
    db.users.splice(exist_email, 1);

    fs.writeFileSync("./db.json", JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log("writing to " + fileName);
    });

    return res.status(204).json({
      status: 204,
      message: "Success",
    });
  } else {
    return res.status(401).json({
      status: 401,
      message: "Email is not found!!",
    });
  }
});

//view all orders
server.get("/auth/order", (req, res) => {
  res.status(200).json({
    status: 200,
    message: "Success",
    data: {
      orders: db.orders,
    },
  });
});

//view order by id
server.get("/auth/order/:id", (req, res) => {
  const { phone } = req.body;
  const order_id = req.params.id;

  const exist_order = db.orders.findIndex(
    (order) => order.id == order_id && order.customer.phone === phone
  );
  if (exist_order !== -1) {
    res.status(200).json({
      status: 200,
      data: {
        orders: db.orders[exist_order],
      },
    });
  } else {
    return res.status(401).json({
      status: 401,
      message: "Order not found!!",
    });
  }
});

//add new order
server.post("/auth/order", (req, res) => {
  const { product, name, phone, location } = req.body;
  let exist_edition_id = null;
  const exist_product_id = db.products.findIndex((item) => {
    return item.id === product.product_id;
  });

  // console.log(exist_edition);

  if (exist_product_id !== -1) {
    exist_edition_id = db.products[exist_product_id].editions.findIndex(
      (item) => item.id === product.edition_id
    );

    if (exist_edition_id === -1) {
      res.status(401).json({
        status: 401,
        message: "Edition not found",
      });
    }
  } else {
    res.status(401).json({
      status: 401,
      message: "Product not found",
    });
  }

  const exist_product = db.products[exist_product_id];
  const exist_edition =
    db.products[exist_product_id].editions[exist_edition_id];
  console.log(exist_edition.quantity, product.quantity);
  if (exist_edition.quantity >= product.quantity) {
    const new_order = {
      id: db.orders.length + 1,
      product: {
        name: exist_product.name,
        edition: exist_edition.version,
        price: exist_edition.price,
      },
      customer: {
        name,
        phone,
        city: location.city,
        district: location.district,
        ward: location.ward,
        street: location.street,
      },
      quantity: product.quantity,
      timestamp: new Date().getTime(),
    };

    db.products[exist_product_id].editions[exist_edition_id].quantity =
      exist_edition.quantity - product.quantity;
    db.orders.push(new_order);

    fs.writeFileSync("./db.json", JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log("writing to " + fileName);
    });
    return res.status(200).json({
      status: 200,
      message: "Success",
      data: new_order,
    });
  } else {
    return res.status(401).json({
      status: 401,
      message: "This edition is out of stock!!",
    });
  }
});

//delete order by id
server.delete("/auth/order/:id", (req, res) => {
  const order_id = req.params.id;

  const exist_order = db.orders.findIndex((order) => order.id == order_id);
  if (exist_order !== -1) {
    db.orders.splice(exist_order, 1);

    fs.writeFileSync("./db.json", JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log("writing to " + fileName);
    });

    return res.status(204).json({
      status: 204,
      message: "Success",
    });
  } else {
    return res.status(401).json({
      status: 401,
      message: "Order not found!!",
    });
  }
});

//update username
server.patch("/auth/order/:id", (req, res) => {
  const order_id = req.params.id;
  const { name, phone, location } = req.body;

  const exist_order = db.orders.findIndex((order) => order.id == order_id);
  if (exist_order !== -1) {
    db.orders[exist_order].customer.name =
      name || db.orders[exist_order].customer.name;
    db.orders[exist_order].customer.phone =
      phone || db.orders[exist_order].customer.phone;

    for (const key in location) {
      db.orders[exist_order].customer[key] = location[key];
    }

    fs.writeFileSync("./db.json", JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log("writing to " + fileName);
    });

    res.status(200).json({
      status: 200,
      message: "Success",
      data: {
        orders: db.orders[exist_order],
      },
    });
  } else {
    res.status(401).json({
      status: 401,
      message: "Order not found!!",
    });
  }
});

server.use(router);

server.listen(PORT, () => {
  console.log("Run Auth API Server");
});
