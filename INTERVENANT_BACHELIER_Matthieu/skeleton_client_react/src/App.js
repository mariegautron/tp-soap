import React, { useState } from 'react'
import { useLocation, Switch, Route, Link } from 'react-router-dom'
import clsx from 'clsx'
import { makeStyles } from '@material-ui/core/styles'
import CssBaseline from '@material-ui/core/CssBaseline'
import AppBar from '@material-ui/core/AppBar'
import Divider from '@material-ui/core/Divider'
import Drawer from '@material-ui/core/Drawer'
import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import ListItemIcon from '@material-ui/core/ListItemIcon'
import ListItemText from '@material-ui/core/ListItemText'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import { MenuBook, Person } from '@material-ui/icons'
import Authors from './authors/Authors'
import Books from './books/Books'
const drawerWidth = 240

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex'
  },
  drawer: {
    [theme.breakpoints.up('sm')]: {
      width: drawerWidth,
      flexShrink: 0
    }
  },
  appBar: {
    marginLeft: drawerWidth,
    [theme.breakpoints.up('sm')]: {
      width: `calc(100% - ${drawerWidth}px)`
    }
  },
  menuButton: {
    marginRight: theme.spacing(2),
    [theme.breakpoints.up('sm')]: {
      display: 'none'
    }
  },
  toolbar: theme.mixins.toolbar,
  drawerPaper: {
    width: drawerWidth
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3)
  }
}))

const MyAppBar = () => {
  const classes = useStyles()
  const location = useLocation()
  let title = ''
  switch (location.pathname) {
    default:
    case '/':
      title = "Page d'accueil"
      break
    case '/auteurs':
      title = 'Auteurs'
      break
    case '/livres':
      title = 'Livres'
      break
  }
  return (
    <AppBar position="absolute" className={clsx(classes.appBar, classes.appBarShift)}>
      <Toolbar className={classes.toolbar}>
        <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
          {title}
        </Typography>
      </Toolbar>
    </AppBar>
  )
}

const Home = () => {
  return (
    <>
      <div>Sélectionner une page</div>
      <img src={process.env.PUBLIC_URL + 'logo512.png'} alt="Logo Ynov" />
    </>
  )
}

export default function App() {
  const classes = useStyles()
  const [open, setOpen] = useState(true)
  return (
    <>
      <div className={classes.root}>
        <CssBaseline />
        <MyAppBar />
        <nav className={classes.drawer} aria-label="mailbox folders">
          <Drawer
            variant="persistent"
            open={open}
            onClose={() => setOpen(false)}
            classes={{
              paper: classes.drawerPaper
            }}
          >
            <div>
              <div className={classes.toolbar} />
              <Divider />
              <List>
                <ListItem component={Link} to="/auteurs">
                  <ListItemIcon>
                    <Person />
                  </ListItemIcon>
                  <ListItemText primary="Auteurs" />
                </ListItem>
                <ListItem component={Link} to="/livres">
                  <ListItemIcon>
                    <MenuBook />
                  </ListItemIcon>
                  <ListItemText primary="Livres" />
                </ListItem>
              </List>
            </div>
          </Drawer>
        </nav>
        <main className={classes.content}>
          <div className={classes.toolbar} />
          <Switch>
            <Route exact path="/" render={() => <Home />} />
            <Route path="/auteurs" render={() => <Authors />} />
            <Route path="/livres" render={() => <Books />} />
          </Switch>
        </main>
      </div>
    </>
  )
}
