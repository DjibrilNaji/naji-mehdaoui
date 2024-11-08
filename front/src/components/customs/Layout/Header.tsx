import Link from "next/link"

import { DropdownMenuProfile } from "@/components/customs/Profile/DropdownMenuProfile"
import routes from "@/routes"

export default function Header() {
  return (
    <div className="flex flex-col py-4 px-4 md:px-14 gap-5 border-b">
      <div className="flex items-center justify-between">
        <Link href={routes.home}>PIMBER</Link>
        <DropdownMenuProfile />
      </div>
    </div>
  )
}
