import { ReactNode } from "react"

import Header from "@/components/customs/Layout/Header"

interface LayoutProps {
  children?: ReactNode
}

export function Layout({ children }: LayoutProps) {
  return (
    <div className="h-screen flex flex-col">
      <Header />
      {children}
    </div>
  )
}
