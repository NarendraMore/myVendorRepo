import { Component, EventEmitter, HostListener, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { navbarData } from './menus';



interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  sidebar: boolean = false;

  checked1: boolean = false;

  sidebar1: boolean = false;
  user!: string;
  logUser: boolean = false;
  isActive:boolean=false;
  isActive1:boolean=false;
  isActive2:boolean=false;
  isActive3:boolean=false;
  isActive4:boolean=false;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
    this.user = JSON.stringify(localStorage.getItem('user'));
  }

  sideBar() {
    if (this.sidebar == false) {
      this.sidebar = true;
    } else {
      this.sidebar = false;
    }
  }

  logout() {
    localStorage.removeItem('user');
    this.router.navigate(['/']);
  }

  onClickHome() {
    this.router.navigate(['/home']);
  }

  onClicUserMngmnt() {
    console.log("inside user manegment")
    this.router.navigate(['/user-mng']);
  }

  onClicRoleMngmnt() {
    this.router.navigate(['/role-mng']);
  }

  onClicVendorMngmnt() {
    this.router.navigate(['/vendor-mng']);
  }

  onClicProjectMngmnt(){
    this.router.navigate(['/project-mng']);
    
  }

  onClicTemplateMngmnt(){
    this.router.navigate(['/template-mng']);
  }

  onClickLibrary(){
    this.router.navigate(['/template-mng']);    
  }

  // config: boolean = false;
  // onClickConfig() {
  //   this.config = true;
  // }





  onClickAnchor(){
    this.isActive=true;
    this.isActive1=false;
    this.isActive2=false;
    this.isActive3=false;
    this.isActive4=false;
  }

  onClickAnchor1(){
    this.isActive=false;
    this.isActive1=true;
    this.isActive2=false;
    this.isActive3=false;
    this.isActive4=false;
  }


  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  collapsed = false;
  screenWidth = 0;
  navData = navbarData;

  isSideNavCollapsed=false;
  
  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.screenWidth = window.innerWidth;
    if(this.screenWidth <= 768 ) {
      this.collapsed = false;
      this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
    }
  }

  toggleCollapse(): void {
    if(this.collapsed===true)
    {
      this.collapsed=false;
    }
    else{
      this.collapsed=true;
    }
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }

  toggleCollapse1(): void {
    if(this.collapsed===true)
    {
      this.collapsed=false;
    }
    else{
      this.collapsed=false;
    }
  }

  closeSidenav(): void {
    this.collapsed = false;
    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
  }


  
}
